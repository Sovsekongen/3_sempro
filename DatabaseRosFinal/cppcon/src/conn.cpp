#include "conn.h"

Conn::Conn()
{
  try
  {
      driver = get_driver_instance();
      con = driver->connect("localhost:3306", "gruppe5", "123");

      con->setSchema("UR5");
      stmt = con->createStatement();
  }
  catch (sql::SQLException &e)
  {
      std::cout << "ERR: " << e.what() << std::endl;
  }
}

Conn::Conn(std::string url, std::string user, std::string pwd)
{
    try
    {
        driver = get_driver_instance();
        con = driver->connect(url, user, pwd);

        con->setSchema("UR5");
        stmt = con->createStatement();
    }
    catch (sql::SQLException &e)
    {
        std::cout << "ERR: " << e.what() << std::endl;
    }
}

void Conn::insertFromUR(int pickUpTime, int throwTime)
{
    try
    {
        cycleTime = openCVExeTime + pickUpTime + throwTime;
        std::stringstream query;
        query << "UPDATE ExeTime SET pickUpTime = " << pickUpTime << ", throwTime = " <<
                             throwTime << ", cycleTime = " << cycleTime << " WHERE throwNr = " << throwNrBuf  << ";";
        stmt->executeUpdate(query.str());
    }
    catch (sql::SQLException &e)
    {
        std::cout << "ERR UR: " << e.what() << std::endl;
    }

}

void Conn::insertFromVision(std::string date, int posX, int posY, int cvTime, int radius, std::string colour, std::string shape, std::string pic)
{
    try
    {
        openCVExeTime = cvTime;
        std::stringstream query1, query2, query3;
        query1 << "INSERT INTO PickUpLoc (PosX, PosY, Time) VALUES (" << posX << "," << posY << ",'" << date << "');";
        query2 << "INSERT INTO ExeTime (throwNr, openCVTime) VALUES (LAST_INSERT_ID(), " << cvTime << ");";
        query3 << "INSERT INTO PickUpObject VALUES (LAST_INSERT_ID(), " << radius << ", '" << colour << "', '" << shape << "', '" << pic << "');";

        stmt->executeUpdate(query1.str());
        stmt->executeUpdate(query2.str());
        stmt->executeUpdate(query3.str());

        std::string throwNr1 = "SELECT throwNr FROM PickUpLoc ORDER BY throwNr DESC LIMIT 1;";
        rs = stmt->executeQuery(throwNr1);
        rs->next();
        throwNrBuf = rs->getInt("throwNr");
    }
    catch (sql::SQLException &e)
    {
        std::cout << "ERR CV: " << e.what() << std::endl;
    }
}

Conn::~Conn()
{
    stmt->close();
    con->close();
    rs->close();
    stmt = nullptr;
    driver = nullptr;
    con = nullptr;
    rs = nullptr;
    delete this;
}

