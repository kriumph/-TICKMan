import hashlib
import sys
from Crypto.Hash import MD5

hashed_pw = input().strip("\n")
file = open("rockyou1.txt")
passwords = file.readlines()
for pw in passwords:
    pw =  pw.strip() + "$you'll_never_guess_this_one$"
    result = hashlib.md5(pw.encode())
    if result.hexdigest() == hashed_pw:
        print(pw)