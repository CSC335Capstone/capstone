package application;

public class MunchkinCardFactory extends CardFactory {
	
	public Card createCard(CARD_TYPE cardType){
		Card returnCard;
		switch(cardType){
		case CLASS:
			returnCard = new ClassCard();
			break;
		case RACE:
			returnCard = new RaceCard();
			break;
		case CURSE:
			returnCard = new CurseCard();
			break;
		case TREASURE:
			returnCard = new TreasureCard();
			break;
		case MONSTER:
			returnCard = new MonsterCard();
			break;
		case HELP:
			returnCard = new HelpCard();
			break;
		default:
			returnCard = null;
			
		}
		return returnCard;
	}

}
