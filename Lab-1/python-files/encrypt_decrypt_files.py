from cryptography.fernet import Fernet

def generate_key():
    key = Fernet.generate_key()
    return key

def encrypt_file(key, filename):
    data = read_file(filename)
    f = Fernet(key) 
    encrypted_data = f.encrypt(data);
    with open(filename+"enc.txt", "wb") as file:
        file.write(encrypted_data)

def read_file(filename):
    with open(filename+".txt", "rb") as file:
        file_data = file.read()
    return file_data

def decrypt_file(key, filename):
    encrypted_data = read_file(filename)
    f = Fernet(key)
    with open(filename+"enc.txt", "rb") as file:
        encrypted_data = file.read()
    decrypted_data = f.decrypt(encrypted_data)
    with open(filename+"dec.txt", "wb") as file:
        file.write(decrypted_data)

if __name__ == '__main__':
    key = generate_key()
    encrypt_file(key, "secret")
    decrypt_file(key, "secret")


