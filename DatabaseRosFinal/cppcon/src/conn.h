#ifndef CONN_H
#define CONN_H

#include "stdlib.h"
#include "iostream"
#include "string"
#include "sstream"
#include "cstddef"

#include "mysql_connection.h"

#include "jdbc/cppconn/driver.h"
#include "jdbc/cppconn/exception.h"
#include "jdbc/cppconn/resultset.h"
#include "jdbc/cppconn/statement.h"

#include "mysqlx/xapi.h"

class Conn
{
public:
    Conn();
    ~Conn();
    Conn(std::string url, std::string user, std::string pwd);

    void insertFromVision(std::string date, int posX, int posY, int cvTime, int radius, std::string colour, std::string shape, std::string pic);
    void insertFromUR(int pickUpTime, int throwTime);

private:
    sql::Driver *driver;
    sql::Connection *con;
    sql::Statement *stmt;
    sql::ResultSet *rs;

    int posX, posY, throwNr, throwNrBuf, openCVExeTime, cycleTime;

};

#endif // CONNECTOR_H
