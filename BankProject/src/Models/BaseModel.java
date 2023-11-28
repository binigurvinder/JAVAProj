package Models;

import java.util.Date;

public class BaseModel {
	
	   protected Date creationDate;
	   protected boolean isActive;
	   protected boolean isDeleted;
	   protected Date dateUpdated;
	    
		public Date getCreationDate() {
			return creationDate;
		}
		public void setCreationDate(Date creationDate) {
			this.creationDate = creationDate;
		}
		public boolean isActive() {
			return isActive;
		}
		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}
		public boolean isDeleted() {
			return isDeleted;
		}
		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
		
		public Date getDateUpdated() {
			return dateUpdated;
		}
		public void setDateUpdated(Date dateUpdated) {
			this.dateUpdated = dateUpdated;
		}

}
