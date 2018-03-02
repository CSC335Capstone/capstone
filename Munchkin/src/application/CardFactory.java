package application;

public abstract class CardFactory {
	public Card orderCard(CARD_TYPE cardType){
		
		Card returnCard;
		returnCard = createCard(cardType);
		return returnCard;
	}
	protected abstract Card createCard(CARD_TYPE cardType);

}
