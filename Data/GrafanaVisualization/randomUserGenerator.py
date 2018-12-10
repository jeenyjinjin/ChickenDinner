import datetime
import random
import string
import pymysql.cursors

def random_date(start, end):
    """Generate a random datetime between `start` and `end`"""
    return start + datetime.timedelta(
        # Get a random amount of seconds between `start` and `end`
        seconds=random.randint(0, int((end - start).total_seconds())),
    )

def user_id_generator(size=6, chars=string.ascii_uppercase + string.digits):
    return ''.join(random.choice(chars) for _ in range(size))


def school_generator():
    weighted_random = ['SMU'] * 60 + ['Other Universities'] * 20+['JC'] * 5 +['Polytechnic'] * 10 + ['International School'] * 5
    return random.choice(weighted_random)

def user_name_generator(size=7, chars=string.ascii_uppercase + string.digits):
    return ''.join(random.choice(chars) for _ in range(size))

def password_generator(size=9, chars=string.ascii_uppercase + string.digits):
    return ''.join(random.choice(chars) for _ in range(size))

def email_generator(size=6, chars=string.ascii_uppercase + string.digits):
    return (''.join(random.choice(chars) for _ in range(size))+"@gmail.com")

def usertype_generator():
    weighted_random =['Admin'] * 10 + ['Student'] * 90
    return  random.choice(weighted_random)

def sqlconn():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='track2career',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)
    return connection

def main():

    generatedtable = list()
    for c in range(1,1000):
        dice = random.randint(0,7)

        singleinstance = (user_id_generator(),usertype_generator(),user_name_generator(),password_generator(),email_generator(),school_generator())
        generatedtable.append(singleinstance)

    print (generatedtable)
    connection = sqlconn()
    ##
    try:
        with connection.cursor() as cursor:
            cursor.executemany("""insert into user (user_id,user_type, username,password,email,school) values (%s,%s,%s,%s,%s,%s)""",generatedtable)

        connection.commit()
    finally:
        connection.close()
        print("Random data inserted successful")


if __name__ == '__main__':
    main()