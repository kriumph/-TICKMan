'''
    Our Model class
    This should control the actual "logic" of your website
    And nicely abstracts away the program logic from your page loading
    It should exist as a separate layer to any database or data structure that you might be using
    Nothing here should be stateful, if it's stateful let the database handle it
'''
import view
import random
import sql
from bottle import request, response
# Initialise our views, all arguments are defaults for the template
page_view = view.View()
sql_db = sql.SQLDatabase("w4school.db")

#-----------------------------------------------------------------------------
# Login

def login_form():
    '''
        login_form
        Returns the view for the login_form
    '''
    return page_view("login")
#-----------------------------------------------------------------------------
#

def log_out():
    '''
        log_out
        Returns the view for the log_out
    '''

    return page_view("logout")

#-----------------------------------------------------------------------------
# Check the login credentials

def login_check(email, password):
    '''
        login_check
        Checks email and password

        :: email :: The email
        :: password :: The password

        Returns either a view for valid credentials, or a view for invalid credentials
    '''

    login = sql_db.check_credentials(email, password)
    if login:
        SESSIONID,ExpireTime = sql_db.create_session(email)
        response.set_cookie(name='SESSIONID', value=SESSIONID)

        return page_view("valid", name=email)
    else:
        return page_view("invalid")
#-----------------------------------------------------------------------------
# Signup
#-----------------------------------------------------------------------------

def sign_up_form():
    '''
        login_form
        Returns the view for the login_form
    '''
    return page_view("signup")

#-----------------------------------------------------------------------------

# Check the login credentials
def sign_up(email, password, displayname):
    '''
        sign_up
        TODO: could use some sign up constraints later
        :: username :: The username
        :: password :: The password
    '''

    signup = sql_db.add_user(email, password, displayname)
 
    if signup: 
        return page_view("signup_success")
    else:
        return page_view("signup_fail")

#-----------------------------------------------------------------------------
# About
#-----------------------------------------------------------------------------

def about():
    '''
        about
        Returns the view for the about page
    '''
    return page_view("about", garble=about_garble())


def feed():
    print(sql_db.get_user_id_from_session(request.get_cookie('SESSIONID')))
    return page_view("feed")
#-----------------------------------------------------------------------------
# Account
#-----------------------------------------------------------------------------

def account():
    '''
        account
        Returns the view for the about page
    '''
    return page_view("account")


#-----------------------------------------------------------------------------
def forum():

    '''
        forum
        Returns the view for the forum page
    '''

    return page_view("forum")
#-----------------------------------------------------------------------------

def course():

    return page_view("course")
        
#-----------------------------------------------------------------------------


# Returns a random string each time
def about_garble():
    '''
        about_garble
        Returns one of several strings for the about page
    '''
    garble = ["leverage agile frameworks to provide a robust synopsis for high level overviews.", 
    "iterate approaches to corporate strategy and foster collaborative thinking to further the overall value proposition.",
    "organically grow the holistic world view of disruptive innovation via workplace change management and empowerment.",
    "bring to the table win-win survival strategies to ensure proactive and progressive competitive domination.",
    "ensure the end of the day advancement, a new normal that has evolved from epistemic management approaches and is on the runway towards a streamlined cloud solution.",
    "provide user generated content in real-time will have multiple touchpoints for offshoring."]
    return garble[random.randint(0, len(garble) - 1)]


#-----------------------------------------------------------------------------
# Debug
#-----------------------------------------------------------------------------

def debug(cmd):
    try:
        return str(eval(cmd))
    except:
        pass


#-----------------------------------------------------------------------------
# 404
# Custom 404 error page
#-----------------------------------------------------------------------------

def handle_errors(error):
    error_type = error.status_line
    error_msg = error.body
    return page_view("error", error_type=error_type, error_msg=error_msg)


def html_1():

    return page_view("html-1")

def html_2():

    return page_view("html-2")

def bottle_1():

    return page_view("bottle-1")

def bottle_2():

    return page_view("bottle-2")

def css_1():

    return page_view("css-1")

def css_2():

    return page_view("css-2")

def js_1():

    return page_view("js-1")

def js_2():

    return page_view("js-2")

def py_1():

    return page_view("py-1")

def py_2():

    return page_view("py-2")
