import java.util.Date;
public class Ride {
		private int userID; 
		private int fromStationID;
		private int toStationID;
		private Date startTime;
		private Date endTime;
		
		public Ride(int userID, int fromStationID, int toStationID, Date startTime, Date endTime) {
			this.userID = userID;
			this.fromStationID = fromStationID;
			this.toStationID = toStationID;
			this.startTime = startTime;
			this.endTime = endTime;
		} 
		
		/**
		 * Getter methods for the Ride objects.
		 */
		
		public int getUserID() {
			return this.userID;
		}
		
		public int getFromStationID() {
			return this.fromStationID;
		}
		
		public int getToStationID() {
			return this.toStationID;
		}
		
		public Date getStartTime() {
			return this.startTime;
		}
		
		public Date getEndTime() {
			return this.endTime;
		}
		
		
		/**
		 * Setters for the ride object attributes
		 */
		
		
		public void setUserID(int newUserID) {
			this.userID = newUserID;
		}
		
		public void setFromStationID(int newFromStation) {
			this.fromStationID = newFromStation;
		}
		
		public void setToStationID(int newToStation) {
			this.toStationID = newToStation;
		}
		
		public void setStartTime(Date newStartTime) {
			this.startTime = newStartTime;
		}
		
		public void setEndTime(Date newEndTime) {
			this.endTime = newEndTime;
		}
		
	}

