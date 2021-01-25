from cryptography.fernet import Fernet

def generate_key():
    key = Fernet.generate_key()
    return key

def encrypt_message(key, message):
    f = Fernet(key) 
    encrypted_message = f.encrypt(message.encode())
    return encrypted_message

def decrypt_message(key, encrypted_message):
    f = Fernet(key) 
    message = f.decrypt(encrypted_message).decode()
    return message

if __name__ == '__main__':
    message = "my secret message!"
    key = generate_key()
    print("original message: ", message)
    encrypted_message = encrypt_message(key, message)
    print("encrypted message: ",encrypted_message)
    decrypted_message = decrypt_message(key, encrypted_message)
    print("decrypted message: ",decrypted_message)

