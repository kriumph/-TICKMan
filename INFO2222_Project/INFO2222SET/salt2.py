import hashlib
from hashlib import md5

hashed = "72a40ac74b7a2472826f306f02e508fc"
with open("rockyou1.txt","r") as dic:
    all_passwords = dic.readlines()
    for pw in all_passwords:
        pw = pw.strip()
        output = hashlib.md5(pw.encode())
        if output.hexdigest() == hashed:
            print(pw)