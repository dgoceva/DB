import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', db='testdb',
                      charset='utf8mb4')
try:
    with con.cursor() as cursor:
        cursor.execute('SELECT * FROM cities')
        rows = cursor.fetchall()
        desc = cursor.description

        print(f'{desc[0][0]:<8} {desc[1][0]:<20} {desc[2][0]:>10}')
        for row in rows:
            print(f'{row[0]:<8} {row[1]:<20} {row[2]:>10}')
finally:
    cursor.close()
