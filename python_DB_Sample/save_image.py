import pymysql


def convertToBinaryData(filename):
    # Convert digital data to binary format
    with open(filename, 'rb') as file:
        binaryData = file.read()
    file.close()
    return binaryData


con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')

try:
    with con.cursor() as cursor:
        cursor.execute("DROP TABLE IF EXISTS employee;")
        cursor.execute("CREATE TABLE employee(id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), photo MEDIUMBLOB, biodata MEDIUMTEXT);")
        cursor.execute("""INSERT INTO employee
            (id, name, photo, biodata) VALUES (%s,%s,%s,%s)""",
                       (1, 'Winnie the Pooh',
                        convertToBinaryData(
                            'Winnie-the-Pooh.jpg'),
                        convertToBinaryData('lorem-ipsum.txt')))
    con.commit()
    print('Image and file inserted successfully as a BLOB into employee table')
except con.Error as error:
    print("Failed inserting BLOB data into table {}".format(error))

finally:
    cursor.close()
    con.close()
    print("DB connection is closed")
