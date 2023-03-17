import mariadb

conn = mariadb.connect(
    user='db_user',
    password='db_user_passwd',
    host='localhost',
    database='countries'
)
cur = conn.cursor()

# select
cur.execute('SELECT id,iso,name FROM country WHERE name LIKE ?',
            ('B%',))
for (id, iso, name) in cur:
    print(f'{id}\t{iso}\t{name.capitalize()}')

conn.close()
