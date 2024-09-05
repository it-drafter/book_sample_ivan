
# book_sample_ivan
Backend Java Spring - Practice Project

## Run Locally

Download .zip with all included files, or clone the project instead:

```bash
  git clone https://github.com/it-drafter/book_sample_ivan.git
```

You will need JDK 17 & IntelliJ IDEA IDE installed in your system to compile and run this program.

##  Preparing the MySQL database
Before running this project make sure to prepare the MySQL database:
```sql
create database books;
create user 'springuser'@'localhost' identified by 'ThePassword';
grant all on books.* to 'springuser'@'localhost';