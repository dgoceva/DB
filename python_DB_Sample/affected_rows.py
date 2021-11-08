import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')

try:
    with con.cursor() as cursor:
        cursor.execute("SELECT * FROM cities WHERE id IN (1,2,3)")
        print(f'The query affected {cursor.rowcount} rows')
finally:
    cursor.close()
