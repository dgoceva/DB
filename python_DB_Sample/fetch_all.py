import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')
try:
    with con.cursor() as cursor:
        cursor.execute('SELECT * FROM cities')
        rows = cursor.fetchall()

        for row in rows:
            print(f'{row[0]} {row[1]} {row[2]}')
finally:
    cursor.close()
