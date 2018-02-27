package application;

public class MunchkinCardFactory extends CardFactory {
	
	public Card createCard(String costumeName){
		Card returnCard;
		switch(costumeName){
		case "Class":
			returnCard = new ClassCard();
			break;
		case "Race":
			returnCard = new RaceCard();
			break;
		case "Curse":
			returnCard = new CurseCard();
			break;
		case "Treasure":
			returnCard = new TreasureCard();
			break;
		case "Monster":
			returnCard = new MonsterCard();
			break;
		case "Help":
			returnCard = new HelpCard();
			break;
		default:
			returnCard = null;
			
		}
		return returnCard;
	}

}
