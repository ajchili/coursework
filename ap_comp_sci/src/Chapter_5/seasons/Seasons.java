public class Seasons
{

	private int month;
	private int day;
	
	public Seasons(int month, int day) {
		this.month = month;
		this.day = day;
	}
	
	public String returnSeason() {
		String season = "";
		if (month > 0 && month <= 3) {
			season = "Winter";
			season = seasonByDay(season);
		} else if (month > 3 && month <= 6) {
			season = "Spring";
			season = seasonByDay(season);
		} else if (month > 6 && month <= 9) {
			season = "Summer";
			season = seasonByDay(season);
		} else if (month > 9 && month <= 12) {
			season = "Fall";
			season = seasonByDay(season);
		}
		return season;
	}
	
	private String seasonByDay(String season) {
		if (season.equals("Winter")) {
			if (month % 3 == 0 && day >= 21) {
				season = "Spring";
			}
		} else if (season.equals("Spring")) {
			if (month % 3 == 0 && day >= 21) {
				season = "Summer";
			}
		} else if (season.equals("Summer")) {
			if (month % 3 == 0 && day >= 21) {
				season = "Fall";
			}
		} else if (season.equals("Fall")) {
			if (month % 3 == 0 && day >= 21) {
				season = "Winter";
			}
		}
		return season;
	}
	
}
