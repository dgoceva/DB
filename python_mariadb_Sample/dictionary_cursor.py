import mariadb
import sys

try:
    conn = mariadb.connect(
        user='testuser',
        password='test623',
        host='localhost',
        database='testdb'
    )
    curr = conn.cursor(dictionary=True)

    curr.execute('SELECT * FROM writers LIMIT 4')

    for record in curr:
        print(record['Id'], record['Name'])

except mariadb.Error as e:
    print(f'Error connecting to the database: {e}')
    sys.exit(1)

finally:
    if conn:
        conn.close()
