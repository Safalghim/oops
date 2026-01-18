package model;


public class OfficeRoom {
private int roomId;
private String roomNumber;
private int staffId; // composition


public OfficeRoom() {}


public OfficeRoom(int roomId, String roomNumber, int staffId) {
this.roomId = roomId;
this.roomNumber = roomNumber;
this.staffId = staffId;
}


public int getRoomId() { return roomId; }
public void setRoomId(int roomId) { this.roomId = roomId; }


public String getRoomNumber() { return roomNumber; }
public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }


public int getStaffId() { return staffId; }
public void setStaffId(int staffId) { this.staffId = staffId; }


@Override
public String toString() {
return "OfficeRoom{" +
"roomId=" + roomId +
", roomNumber='" + roomNumber + '\'' +
", staffId=" + staffId +
'}';
}
}