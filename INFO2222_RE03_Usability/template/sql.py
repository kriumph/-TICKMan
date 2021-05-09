import sqlite3
from datetime import datetime
from datetime import timedelta
import uuid 

# This class is a simple handler for all of our SQL database actions
# Practicing a good separation of concerns, we should only ever call 
# These functions from our models

# If you notice anything out of place here, consider it to your advantage and don't spoil the surprise

class SQLDatabase():
    '''
        Our SQL Database

    '''

    # Get the database running
    def __init__(self, database_arg=":memory:"):
        self.conn = sqlite3.connect(database_arg)
        self.cur = self.conn.cursor()

    # SQLite 3 does not natively support multiple commands in a single statement
    # Using this handler restores this functionality
    # This only returns the output of the last command
    def execute(self, sql_string):
        out = None
        for string in sql_string.split(";"):
            out = self.cur.execute(string)
            # try:
            #     out = self.cur.execute(string)
            # except:
            #     print("error on",)
        return out

    # Commit changes to the database
    def commit(self):
        self.conn.commit()

    #-----------------------------------------------------------------------------
    
    # Sets up the database
    # Default admin password
    def database_setup(self, admin_password='admin'):

        # Clear the database if needed
        self.execute("DROP TABLE IF EXISTS Users")
        self.execute("DROP TABLE IF EXISTS Forum")
        self.execute("DROP TABLE IF EXISTS PrivateMessage")
        self.execute("DROP TABLE IF EXISTS Sessions")
        self.execute("DROP TABLE IF EXISTS Message")
        self.commit()

        # Create the users table
        self.execute(
            """
            CREATE TABLE Users(
                Id INTEGER PRIMARY KEY AUTOINCREMENT,
                DisplayName TEXT,
                Email TEXT UNIQUE,
                Password TEXT,
                Muted INTEGER DEFAULT 0,
                Admin INTEGER DEFAULT 0
            );
            """
        )
        #Create Private Messages table
        self.execute(
            """
            CREATE TABLE PrivateMessage(
                ID INTEGER PRIMARY KEY AUTOINCREMENT,
                message VARCHAR(255) NOT NULL,
                sender INTEGER REFERENCES Users(Id) NOT NULL,
                receiver INTEGER REFERENCES Users(Id) NOT NULL
            );
            """
        )
        
        # Create sessions table
        self.execute(
            """
            CREATE TABLE Sessions(
                ID TEXT PRIMAR KEY,
                UserId INTEGER,
                CreatedTime TEXT,
                ExpireTime TEXT,
                LastModified TEXT,
                FOREIGN KEY(UserId) REFERENCES Users(Id)
            );
            """
        )
        # # Create the forum table
        # self.execute(
        #     """
        #     CREATE TABLE Forum(
        #         TimeStamp 
        #     )
        #     """
        # )

        self.commit()

        # Add our admin user
        self.add_user("admin@sydney.edu.au", admin_password, 'admin', admin=1)
    #-----------------------------------------------------------------------------
    # User handling
    #-----------------------------------------------------------------------------

    # Add a user to the database
    def add_user(self, email, password, displayName, admin=0):

        sql_cmd ="""
                INSERT INTO Users('DisplayName', 'Email', 'Password', 'Muted', 'Admin')
                VALUES('{displayName}', '{email}', '{password}', {Muted}, {admin})
            """

        sql_cmd = sql_cmd.format(displayName=displayName, email=email, password=password, Muted=0, admin=admin)

        self.execute(sql_cmd)
        self.commit()
        return True


    #-----------------------------------------------------------------------------

    # Delete a user from the database
    def delete_user(self, email):
        sql_cmd ="""
                DELETE * FROM Users
                WHERE Email = '{email}'
            """

        sql_cmd = sql_cmd.format(email=email)

        self.execute(sql_cmd)
        self.commit()
        if self.cur.fetchone():
            return True
        else:
            return False
    #-----------------------------------------------------------------------------

    def get_user_id_from_email(self, email):
        sql_query ="""
                SELECT Id
                FROM Users
                WHERE Email = '{email}'
            """

        sql_query = sql_query.format(email=email)
        self.execute(sql_query)
        res = self.cur.fetchone()

        if res:
            return res[0]
        else:
            return False

    # Check login credentials
    def check_credentials(self, email, password):
        sql_query ="""
                SELECT *
                FROM Users
                WHERE Email = '{email}' AND Password = '{password}'
            """

        sql_query = sql_query.format(email=email, password=password)
        self.execute(sql_query)

        res = self.cur.fetchone()

        # If our query returns
        if res:
            return res
        else:
            return False


    def create_session(self, email):
        '''
        :param email: the email of the user attempt to login
        return SESSIONID
        '''
        Userid = self.get_user_id_from_email(email)
        SESSIONID = uuid.uuid4().hex[:17].upper()
        sql_cmd ="""
                INSERT INTO Sessions('ID', 'UserId', 'CreatedTime', 'ExpireTime', 'LastModified')
                VALUES('{ID}', '{Userid}', '{CreatedTime}', '{ExpireTime}', '{LastModified}')
            """

        CreatedTime = datetime.now()
        ExpireTime = datetime.now() + timedelta(minutes=15) 

        sql_cmd = sql_cmd.format(ID=SESSIONID, Userid=Userid, CreatedTime=CreatedTime, ExpireTime=ExpireTime, LastModified=CreatedTime)

        self.execute(sql_cmd)
        self.commit()
        return SESSIONID, ExpireTime

    def get_user_id_from_session(self, SESSIONID):
        '''
        :param SESSIONID: Associated id of session returned from client

        RETURN the user id, 'Id' in 'Users' table
        '''
        sql_query ="""
                SELECT UserId
                FROM Sessions
                WHERE ID = '{SESSIONID}'
            """
        sql_query = sql_query.format(SESSIONID=SESSIONID)
        self.execute(sql_query)
        res = self.cur.fetchone()
        # If our query returns
        if res:
            return res[0]
        else:
            return False