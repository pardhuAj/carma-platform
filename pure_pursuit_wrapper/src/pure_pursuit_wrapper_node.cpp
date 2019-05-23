/*
 * Copyright (C) 2018-2019 LEIDOS.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

#include <ros/ros.h>
#include "pure_pursuit_wrapper/pure_pursuit_wrapper.hpp"
#include <ros/console.h>

int main(int argc, char** argv) {
  if (ros::console::set_logger_level(ROSCONSOLE_DEFAULT_NAME, ros::console::levels::Debug)) { // Change the level to fit your needs
    ros::console::notifyLoggerLevelsChanged();
  }

  ros::init(argc, argv, "pure_pursuit_wrapper_node");
  ros::NodeHandle nh("~");

  pure_pursuit_wrapper::PurePursuitWrapper PurePursuitWrapper(nh);

  while (ros::ok() && !PurePursuitWrapper.shutting_down_) {
    ros::spinOnce();
  }

  ROS_INFO("Successfully launched node.");
  return 0;
}
