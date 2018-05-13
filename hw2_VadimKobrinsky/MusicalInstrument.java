// Name: Vadim Kobrinsky
// ID: 314257551

package hw2_VadimKobrinsky;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MusicalInstrument implements InstrumentFunc {
	private Number price;
	private String brand;

	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		if (price instanceof Double) {
			setPrice(price.doubleValue());
		} else if (price instanceof Integer) {
			setPrice(price.intValue());
		}
	}

	public MusicalInstrument(Scanner scanner) {
		Number price = 0;
		String brand;

		try {
			if (scanner.hasNextInt())
				price = scanner.nextInt();
			else if (scanner.hasNextDouble())
				price = scanner.nextDouble();
		} catch (InputMismatchException ex) {
			throw new InputMismatchException("Price not found!");
		}
		setPrice(price);
		scanner.nextLine();
		brand = scanner.nextLine();
		setBrand(brand);
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		if (price instanceof Double && price.doubleValue() > 0)
			this.price = price;
		else if (price instanceof Integer && price.intValue() > 0)
			this.price = price;
		else
			throw new InputMismatchException("Price must be a positive number!");

	}

	protected boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equals(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;

		MusicalInstrument otherInstrument = (MusicalInstrument) o;

		if (getPrice() instanceof Integer) {
			return getPrice().intValue() == otherInstrument.getPrice().intValue()
					&& getBrand().equals(otherInstrument.getBrand());
		} else {
			return getPrice().doubleValue() == otherInstrument.getPrice().doubleValue()
					&& getBrand().equals(otherInstrument.getBrand());
		}
	}

	/**
	 * MusicalInstrument.compareTo(MusicalInstrument) compares to another
	 * MusicalInstrument primarily by lexicographic order and secondarily 
	 * by price.
	 * 
	 * @param MusicalInstruments
	 * @return -1 if the performing instruments is smaller, 0 if equal, 1 if bigger.
	 */
	@Override
	public int compareTo(MusicalInstrument instrument) {
		int javaCompareToResult = this.getBrand().compareTo(instrument.getBrand());
		
		if (javaCompareToResult < 0) {
			return -1;
		} else if (javaCompareToResult > 0) {
			return 1;
		} else {
			if (this.getPrice().doubleValue() < instrument.getPrice().doubleValue()) {
				return -1;
			} else if (this.getPrice().doubleValue() > instrument.getPrice().doubleValue()) {
				return 1;
			} else {
				return 0;
			}

		}

	}
	
	@Override
	public MusicalInstrument clone() {
		return (MusicalInstrument) this.clone();
		
	}

	@Override
	public String toString() {
		if (getPrice() instanceof Double) {
			return String.format("%-8s %-9s| Price: %7.1f,", getBrand(), getClass().getSimpleName(),
					getPrice().doubleValue());
		} else {
			return String.format("%-8s %-9s| Price: %7d,", getBrand(), getClass().getSimpleName(),
					getPrice().intValue());
		}

	}
}
