import hashlib
import numpy as np
from hashlib import md5

def initialise_tfa(seed, n_init=1):
    hash = md5(seed.encode()).hexdigest()[:4]
    for _ in range(n_init):
        hash = update_tfa(hash)
    return hash

def update_tfa(hash):
    hash = md5(hash.encode()).hexdigest()[:4]
    return hash

salt = "8b283e8957f744ae5a1a6add05fc354f"
password = ""
with open('rockyou1.txt', 'r') as f:
    while True:
        password = f.readline().strip()
        pre = md5(password.encode()).hexdigest()
        if md5(pre.encode()).hexdigest() == salt:
            print(password)
            break

hashed_password = md5(password.encode()).hexdigest()

salt = md5(hashed_password.encode()).hexdigest()
salted_hashed_password = md5((hashed_password + salt).encode()).hexdigest()

two_factor_init_n = int(np.random.rand() * 5) + 1
two_factor = initialise_tfa(salted_hashed_password, two_factor_init_n)
print(two_factor)