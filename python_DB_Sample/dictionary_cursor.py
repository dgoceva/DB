import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', db='testdb',
                      charset='utf8mb4',
                      cursorclass=pymysql.cursors.DictCursor)
try:
    with con.cursor() as cursor:
        cursor.execute('SELECT * FROM cities')
        rows = cursor.fetchall()

        for row in rows:
            print(f"{row['id'], row['name']}")
finally:
    cursor.close()
