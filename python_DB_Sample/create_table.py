import pymysql

con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')

try:
    with con.cursor() as cursor:
        cursor.execute("DROP TABLE IF EXISTS cities");
        cursor.execute("CREATE TABLE cities(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), population INT)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Bratislava', 432000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Budapest', 1759000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Prague', 1280000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Warsaw', 1748000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Los Angeles', 3971000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('New York', 8550000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Edinburgh', 464000)");
        cursor.execute("INSERT INTO cities(name, population) VALUES('Berlin', 3671000)");

        con.commit()

        print('Done.')
finally:
    cursor.close()
