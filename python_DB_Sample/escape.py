import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')

# user input
myid = 4

try:
    with con.cursor() as cursor:
        cursor.execute("SELECT * FROM cities WHERE id=%s", myid)
        (cid, name, population) = cursor.fetchone()
        print(cid, name, population)
finally:
    cursor.close()
