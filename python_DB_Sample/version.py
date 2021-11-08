import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')
try:
    with con.cursor() as cursor:
        cursor.execute('SELECT VERSION()')
        version = cursor.fetchone()
        print(f'Database version: {version[0]}')
finally:
    cursor.close()
