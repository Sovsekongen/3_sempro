// Auto-generated. Do not edit!

// (in-package beginner_tutorials.msg)


"use strict";

const _serializer = _ros_msg_utils.Serialize;
const _arraySerializer = _serializer.Array;
const _deserializer = _ros_msg_utils.Deserialize;
const _arrayDeserializer = _deserializer.Array;
const _finder = _ros_msg_utils.Find;
const _getByteLength = _ros_msg_utils.getByteLength;
let std_msgs = _finder('std_msgs');

//-----------------------------------------------------------

class urmsg {
  constructor(initObj={}) {
    if (initObj === null) {
      // initObj === null is a special case for deserialization where we don't initialize fields
      this.header = null;
      this.pickUpTime = null;
      this.throwTime = null;
    }
    else {
      if (initObj.hasOwnProperty('header')) {
        this.header = initObj.header
      }
      else {
        this.header = new std_msgs.msg.Header();
      }
      if (initObj.hasOwnProperty('pickUpTime')) {
        this.pickUpTime = initObj.pickUpTime
      }
      else {
        this.pickUpTime = 0;
      }
      if (initObj.hasOwnProperty('throwTime')) {
        this.throwTime = initObj.throwTime
      }
      else {
        this.throwTime = 0;
      }
    }
  }

  static serialize(obj, buffer, bufferOffset) {
    // Serializes a message object of type urmsg
    // Serialize message field [header]
    bufferOffset = std_msgs.msg.Header.serialize(obj.header, buffer, bufferOffset);
    // Serialize message field [pickUpTime]
    bufferOffset = _serializer.uint32(obj.pickUpTime, buffer, bufferOffset);
    // Serialize message field [throwTime]
    bufferOffset = _serializer.uint32(obj.throwTime, buffer, bufferOffset);
    return bufferOffset;
  }

  static deserialize(buffer, bufferOffset=[0]) {
    //deserializes a message object of type urmsg
    let len;
    let data = new urmsg(null);
    // Deserialize message field [header]
    data.header = std_msgs.msg.Header.deserialize(buffer, bufferOffset);
    // Deserialize message field [pickUpTime]
    data.pickUpTime = _deserializer.uint32(buffer, bufferOffset);
    // Deserialize message field [throwTime]
    data.throwTime = _deserializer.uint32(buffer, bufferOffset);
    return data;
  }

  static getMessageSize(object) {
    let length = 0;
    length += std_msgs.msg.Header.getMessageSize(object.header);
    return length + 8;
  }

  static datatype() {
    // Returns string type for a message object
    return 'beginner_tutorials/urmsg';
  }

  static md5sum() {
    //Returns md5sum for a message object
    return 'a2e9cc88ef89af17cbcd6d11a52269cb';
  }

  static messageDefinition() {
    // Returns full string definition for message
    return `
    Header header
    uint32 pickUpTime
    uint32 throwTime
    
    ================================================================================
    MSG: std_msgs/Header
    # Standard metadata for higher-level stamped data types.
    # This is generally used to communicate timestamped data 
    # in a particular coordinate frame.
    # 
    # sequence ID: consecutively increasing ID 
    uint32 seq
    #Two-integer timestamp that is expressed as:
    # * stamp.sec: seconds (stamp_secs) since epoch (in Python the variable is called 'secs')
    # * stamp.nsec: nanoseconds since stamp_secs (in Python the variable is called 'nsecs')
    # time-handling sugar is provided by the client library
    time stamp
    #Frame this data is associated with
    # 0: no frame
    # 1: global frame
    string frame_id
    
    `;
  }

  static Resolve(msg) {
    // deep-construct a valid message object instance of whatever was passed in
    if (typeof msg !== 'object' || msg === null) {
      msg = {};
    }
    const resolved = new urmsg(null);
    if (msg.header !== undefined) {
      resolved.header = std_msgs.msg.Header.Resolve(msg.header)
    }
    else {
      resolved.header = new std_msgs.msg.Header()
    }

    if (msg.pickUpTime !== undefined) {
      resolved.pickUpTime = msg.pickUpTime;
    }
    else {
      resolved.pickUpTime = 0
    }

    if (msg.throwTime !== undefined) {
      resolved.throwTime = msg.throwTime;
    }
    else {
      resolved.throwTime = 0
    }

    return resolved;
    }
};

module.exports = urmsg;
