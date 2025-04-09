import pymysql


def write_file(data, filename, mode):
    # Convert binary data to proper format and write it on Hard Disk
    with open(filename, mode) as file:
        file.write(data)
    file.close()


con = pymysql.connect(host='localhost', user='user7',
                      password='s$cret', database='testdb')

try:
    with con.cursor() as cursor:
        cursor.execute("SELECT * FROM employee WHERE id=%s", 1)
        record = cursor.fetchall()
        for row in record:
            print("Id = ", row[0], )
            print("Name = ", row[1])
            image = row[2]
            file = row[3]
            print("Storing employee image and bio-data on disk \n")
            write_file(
                image, 'Winnie-the-Pooh-out.jpg','wb')
            write_file(
                file, 'lorem-ipsum-out.txt','w')

except con.Error as error:
    print("Failed to read BLOB data from table {}".format(error))

finally:
    cursor.close()
    con.close()
    print("DB connection is closed")
