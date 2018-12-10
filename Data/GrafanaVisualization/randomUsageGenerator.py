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

def id_generator(size=6, chars=string.ascii_uppercase + string.digits):
    return ''.join(random.choice(chars) for _ in range(size))


def sqlconn():
    connection = pymysql.connect(host='localhost',
                                 user='root',
                                 password='',
                                 db='track2career',
                                 charset='utf8mb4',
                                 cursorclass=pymysql.cursors.DictCursor)
    return connection

def main():
    tracks = ["Core Courses","Financial Technology","Digital Business Solutioning","Business Analytics","Software Development","Cybersecurity","Artificial Intelligence","Non-Track Elective"]
    start = datetime.datetime(2018, 11, 29, 5, 30, 20)
    end = datetime.datetime.now()
    generatedtable = list()
    for c in range(1,10000):
        dice = random.randint(0,7)

        singleinstance = (random_date(start,end).strftime('%Y-%m-%d %H:%M:%S'),id_generator(),tracks[dice])
        generatedtable.append(singleinstance)

    print (generatedtable)
    connection = sqlconn()
    try:
        with connection.cursor() as cursor:
            cursor.executemany("""insert into accessdetails (accesstime, user_id, accessedtrack) values (%s,%s,%s)""",generatedtable)

        connection.commit()
    finally:
        connection.close()
        print("Random data inserted successful")


if __name__ == '__main__':
    main()