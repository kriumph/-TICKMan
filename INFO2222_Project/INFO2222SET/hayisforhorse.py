import hashlib
from hashlib import md5

hashed = "3f6f3d091584d10126fbb32be70f0d16"
with open("rockyou1.txt","r") as dic:
    all_passwords = dic.readlines()
    for pw in all_passwords:
        pw = pw.strip()
        pw = pw + ":<Mk87dk"
        output = hashlib.md5(pw.encode())
        if output.hexdigest() == hashed:
            print(pw)