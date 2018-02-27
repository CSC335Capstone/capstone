package application;

public abstract class CardFactory {
	public Card orderCard(String cardName){
		
		Card returnCard;
		returnCard = createCard(cardName);
		return returnCard;
	}
	protected abstract Card createCard(String cardName);

}
