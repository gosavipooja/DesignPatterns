package com.pooja.mediatorpattern;

import java.util.ArrayList;

public class StockMediator implements Mediator{

	private ArrayList<Colleague> colleagues;
	private ArrayList<StockOffer> stockBuyOffers;
	private ArrayList<StockOffer> stockSellOffers;
	
	private int colleagueCodes = 0;
	
	public StockMediator() {
		// TODO Auto-generated constructor stub
		colleagues = new ArrayList<Colleague>();
		stockBuyOffers = new ArrayList<StockOffer>();
		stockSellOffers = new ArrayList<StockOffer>();
	}
	
	@Override
	public void addColleague(Colleague colleague) {
		colleagues.add(colleague);
		colleagueCodes++;
		colleague.setColleagueCode(colleagueCodes);
		
	}
	
	@Override
	public void saleOffer(String stock, int shares, int collCode) {
			boolean stockSold = false;
			
			for(StockOffer offer : stockBuyOffers){
				if((offer.getStockSymbol() == stock) && (offer.getStockShares() == shares)){
					
					System.out.println(shares + " shares of " + stock + 
							" sold to colleague code : " + offer.getColleagueCode());
					
					stockBuyOffers.remove(offer);
					stockSold = true;
				}
				
				if(stockSold)	break;
			}
			if(!stockSold){
				
				System.out.println(shares + " shares of " + stock + 
							" added to inventory");
				
				StockOffer newOffer = new StockOffer(shares, stock, collCode);
				stockSellOffers.add(newOffer);
			}
		
	}

	@Override
	public void buyOffer(String stock, int shares, int collCode) {
		boolean stockBuy = false;
		
		for(StockOffer offer : stockSellOffers){
			if((offer.getStockSymbol() == stock) && (offer.getStockShares() == shares)){
				
				System.out.println(shares + " shares of " + stock + 
						" bought by colleague code : " + offer.getColleagueCode());
				
				stockSellOffers.remove(offer);
				stockBuy = true;
			}
			
			if(stockBuy)	break;
		}
		if(!stockBuy){
			
			System.out.println(shares + " shares of " + stock + 
						" added to inventory");
			
			StockOffer newOffer = new StockOffer(shares, stock, collCode);
			stockBuyOffers.add(newOffer);
		}

		
	}
	
	public void getStockOfferings() {
		System.out.println("\n For Sale :");
		for(StockOffer offer : stockSellOffers) {
			System.out.println(offer.getStockShares() + " of " + offer.getStockSymbol());
		}
		
		System.out.println("\n For Buy :");
		for(StockOffer offer : stockBuyOffers) {
			System.out.println(offer.getStockShares() + " of " + offer.getStockSymbol());
		}
	}


}
