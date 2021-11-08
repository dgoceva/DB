import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')

city = (10, 'Kiev', 2887000)
try:
    with con.cursor() as cursor:
        cursor.execute("INSERT INTO cities VALUES (%s,%s,%s)",
                       (city[0], city[1], city[2]))
        con.commit()
        print('New city inserted successfully')
finally:
    cursor.close()
