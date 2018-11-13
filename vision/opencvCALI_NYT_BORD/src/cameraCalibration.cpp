#include "ros/ros.h"
#include "std_msgs/String.h"
#include "std_msgs/Float32MultiArray.h"
#include "opencv2/opencv.hpp"
#include <vector>

std::vector<cv::Point2f> pixelCoor(4);
std::vector<cv::Point2f> realCoor(4);


void CallBackFunc(int event, int x, int y, int, void*){


    if(event == cv::EVENT_LBUTTONDBLCLK){

        cv::Point2i pt;
        pt.x = x;
        pt.y = y;
        pixelCoor.push_back(pt);
        std::cout << pt.x << " " << pt.y << std::endl;


    }
}

int main(int argc, char **argv)
{

    ros::init(argc, argv, "arrayPublisher");

    ros::NodeHandle n;

    ros::Publisher pub = n.advertise<std_msgs::Float32MultiArray>("array", 1000);

    std::vector<cv::String> fileNames;
    cv::glob("/home/peter/images/Image*.png", fileNames, false);
    cv::Size patternSize(14 - 1, 11 - 1);
    std::vector<std::vector<cv::Point2f> > q(fileNames.size());
    cv::TermCriteria term;

    // Detect feature points
    std::size_t i = 0;
    for (int j = 0; j < fileNames.size(); ++j) {
      std::string f;
      f = fileNames[j];

      std::cout << f << std::endl;

      // 1. Read in the image an call cv::findChessboardCorners()
      cv::Mat pic = cv::imread(f, cv::IMREAD_COLOR);

      cv::Mat grayPic;
      cv::cvtColor(pic, grayPic, CV_BGR2GRAY);

      bool success;
      success = cv::findChessboardCorners(grayPic, patternSize, q[i], cv::CALIB_CB_FAST_CHECK);
      // 2. Use cv::cornerSubPix() to refine the found corner detections
      cv::cornerSubPix(grayPic, q[i], cv::Size(8,8), cv::Size(-1,-1), term);

      // Display
    /*  cv::drawChessboardCorners(pic, patternSize, q[i], success);
      //std::cout << "image number " << j << std::endl;
      cv::imshow("chessboard detection", pic);
      cv::waitKey(0);*/

      i++;
    }

      int numberOfSquars;
      float squarSize = 50.0;
      int numCornersHor = 14-1;
      int numCornersVer = 11-1;
      numberOfSquars = numCornersHor * numCornersVer;
      std::vector<cv::Point3f> obj;
      //3. Generate checkerboard (world) coordinates Q. The board has 25 x 18
      //fields with a size of 15x15mm
      //dette er måden at genere 3D koordinatter på
      for(int i = 0; i < numCornersVer; ++i){
          for(int j = 0; j < numCornersHor; ++j){
              cv::Point3f a(j * squarSize, i * squarSize, 0.0);
              obj.push_back(a);
          }
      }
    std::vector<std::vector<cv::Point3f> > Q;

    for(unsigned int i = 0; i < fileNames.size(); ++i){

          Q.push_back(obj);
    }


    std::cout << "here" << std::endl;


    cv::Matx33f K(cv::Matx33f::eye());  // intrinsic camera matrix
    cv::Vec<float, 5> k(0, 0, 0, 0, 0); // distortion coefficients

    std::vector<cv::Mat> rvecs, tvecs;
    std::vector<double> stdIntrinsics, stdExtrinsics, perViewErrors;
    int flags = cv::CALIB_FIX_ASPECT_RATIO + cv::CALIB_FIX_K3 +
                cv::CALIB_ZERO_TANGENT_DIST + cv::CALIB_FIX_PRINCIPAL_POINT;
    cv::Size frameSize(1440, 1080);

    std::cout << "Calibrating..." << std::endl;
    // 4. Call "float error = cv::calibrateCamera()" with the input coordinates
    // and output parameters as declared above...
    //må helst ikke være større end 1, da den fortæller hvor gode dine billeder er til at genere undisorted images
    float error = cv::calibrateCamera(Q, q, frameSize, K, k, rvecs, tvecs, stdIntrinsics, stdExtrinsics, perViewErrors, flags);

    std::cout << "Reprojection error = " << error << "\nK =\n"
              << K << "\nk=\n"
              << k << std::endl;

    // Precompute lens correction interpolation
    cv::Mat mapX, mapY;
    cv::initUndistortRectifyMap(K, k, cv::Matx33f::eye(), K, frameSize, CV_32FC1,mapX, mapY);

    // Opstille perspektiv-matrice



      for (int j = 0; j < fileNames.size(); ++j) {
        std::string f;
        f = fileNames[j];

        cv::Mat img = cv::imread(f, cv::IMREAD_COLOR);

        cv::Mat imgUndistorted(img.rows, img.cols, img.type());
        // 5. Remap the image using the precomputed interpolation maps.
        //lige meget om du kalder undisort eller remap, det giver samme resultat, tænker vi skal bruge undisort, da der er mindre at sende.
        //cv::undistort(img, imgUndistorted, K, k);
        cv::remap(img,imgUndistorted,mapX,mapY,cv::INTER_LINEAR,cv::BORDER_CONSTANT,cv::Scalar(1,1,1));
        // Display

        cv::resize(imgUndistorted,imgUndistorted,cv::Size(500,500));

        //cv::imshow("undistorted image", imgUndistorted);
        //cv::imshow("img", img);
        //cv::waitKey(0);
      }
      cv::Mat input = cv::imread("/home/peter/Imag__2018-11-08__11-24-46.png", cv::IMREAD_COLOR);
      cv::Mat output;
      cv::undistort(input,output,K,k);
      cv::namedWindow("caliwindow",cv::WINDOW_FREERATIO);
      cv::imshow("caliwindow",output);

      std::cout << "Please double click to add point to array" << std::endl;
      char keypress;

      /*while(true){
           cv::setMouseCallback("caliwindow", CallBackFunc);
           keypress = cv::waitKey();
          if(keypress == 'q'){
            //  cv::destroyAllWindows();
              break;
          }
        }*/


    // for(unsigned int i = 0; i < pixelCoor.size() ;++i){
    //    std::cout << pixelCoor[i].x << "," << pixelCoor[i].y << std::endl;
    // }


     pixelCoor.at(0).y = 47;
     pixelCoor.at(0).x = 548;
     pixelCoor.at(1).y = 735;
     pixelCoor.at(1).x = 17;
     pixelCoor.at(2).y = 1368;
     pixelCoor.at(2).x = 100;
     pixelCoor.at(3).y = 1402;
     pixelCoor.at(3).x = 500;


     //Definere møtrikker på det fysiske bord i forhold til hinanden
        // Øverste venstre hjørne er 0,0
     realCoor.at(0).y = 0;
     realCoor.at(0).x = 630;
        //Øverste højre hjørne
     realCoor.at(1).y = 630;
     realCoor.at(1).x = 0;
        //Nederste venstre hjørne
     realCoor.at(2).y = 1190;
     realCoor.at(2).x = 82;
        //Nederste højre hjørne
     realCoor.at(3).y = 1219;
     realCoor.at(3).x = 438;

     cv::Mat result((int)realCoor.at(3).y,(int)realCoor.at(3).x,output.type());

       cv::Mat perspective_matrix = cv::getPerspectiveTransform(pixelCoor,realCoor);
       cv::warpPerspective(output, result,perspective_matrix,result.size(),CV_INTER_LINEAR);

       std::cout << perspective_matrix << std::endl;

       cv::imshow("input",output);
       cv::imshow("output", result);
       cv::waitKey(0);

    // Show lens corrected images
    /*for (int j = 0; j < fileNames.size(); ++j) {
      std::string f;
      f = fileNames[j];

      cv::Mat img = cv::imread(f, cv::IMREAD_COLOR);

      cv::Mat imgUndistorted(img.rows, img.cols, img.type());
      // 5. Remap the image using the precomputed interpolation maps.
      //lige meget om du kalder undisort eller remap, det giver samme resultat, tænker vi skal bruge undisort, da der er mindre at sende.
      //cv::undistort(img, imgUndistorted, K, k);
      cv::remap(img,imgUndistorted,mapX,mapY,cv::INTER_LINEAR,cv::BORDER_CONSTANT,cv::Scalar(1,1,1));
      // Display
      cv::imshow("undistorted image",cv::WINDOW_AUTOSIZE);
      cv::imshow("undistorted image", imgUndistorted);
      //cv::imshow("img", img);
      cv::waitKey(0);
    }*/


    //publicer kun engang
    /*if(ros::ok()){

      std_msgs::Float32MultiArray array;
      std_msgs::Float32MultiArray array1;


      array.data.clear();
      for(int i = 0; i < 5; ++i){
        array.data.push_back(k[i]);
      }

      array1.data.clear();
      for(int i = 0; i < K.rows; ++i){
          for(int j = 0; j < K.cols; ++j){
            array1.data.push_back(K(i,j));
          }
      }

      pub.publish(array);

      ROS_INFO("I published something!");

      sleep(2);

      pub.publish(array1);

      ROS_INFO("I published something!");

      ros::spinOnce();

      sleep(2);
    }*/


  return 0;
}
