// Name: Vadim Kobrinsky
// ID: 314257551

package hw2_VadimKobrinsky;

import java.util.ArrayList;

public class AfekaInventory implements InventoryManagement {

	// Attributes
	private ArrayList<MusicalInstrument> inventoryList;
	private double totalPrice;
	private boolean isSorted;
	private final String inventoryMenu = "\n--------------------------------------------------------------------------\n"
			+ "AFEKA MUSICAL INSTRUMENT INVENTORY MENU\n"
			+ "--------------------------------------------------------------------------\n"
			+ "1. Copy All String Instruments To Inventory\n" + "2. Copy All Wind Instruments To Inventory\n"
			+ "3. Sort Instruments By Brand And Price\n" + "4. Search Instrument By Brand And Price\n"
			+ "5. Delete Instrument\n" + "6. Delete all Instruments\n" + "7. Print Inventory Instruments\n"
			+ "Choose your option or any other key to EXIT\n" + "\nYour Option: ";

	// CTORs
	public AfekaInventory() {
		inventoryList = new ArrayList<MusicalInstrument>();
		totalPrice = 0;
		isSorted = false;
	}

	// Getters and Setters Methods
	protected ArrayList<MusicalInstrument> getInventoryList() {
		return inventoryList;
	}

	protected void setInventoryList(ArrayList<MusicalInstrument> inventoryList) {
		this.inventoryList = inventoryList;
	}

	protected double getTotalPrice() {
		return totalPrice;
	}

	protected void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	protected boolean isSorted() {
		return isSorted;
	}

	protected void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

	protected String getInventoryMenu() {
		return inventoryMenu;
	}

	// Other Methods
	public double addTwoNumbers(Number num1, Number num2) {
		return num1.doubleValue() + num2.doubleValue();
	}

	public void printInventoryMenu() {
		System.out.print(getInventoryMenu());
	}

	public void printInstrumentsInInventory(ArrayList<MusicalInstrument> list) {
		System.out.println("\n--------------------------------------------------------------------------\n"
				+ "AFEKA MUSICAL INSTRUMENT INVENTORY\n"
				+ "--------------------------------------------------------------------------\n");
		if (getInventoryList().isEmpty()) {
			System.out.println(String.format("There Is No Instruments To Show\n\nTotal Price: %.2f\tSorted: %b",
					totalPrice, isSorted));
		} else {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
			System.out.println(String.format("\nTotal Price: %.2f\tSorted: %b", totalPrice, isSorted));
		}

	}

	@Override
	public void addAllStringInstruments(ArrayList<MusicalInstrument> originList,
			ArrayList<MusicalInstrument> destinationList) {
		for (MusicalInstrument originInstrument : originList) {
			if (originInstrument instanceof StringInstrument) {
				destinationList.add(originInstrument);
				setTotalPrice(totalPrice + originInstrument.getPrice().doubleValue());
				setSorted(false);
			}
		}
		System.out.println("\nAll String Instruments Added Successfully!");

	}

	@Override
	public void addAllWindInstruments(ArrayList<MusicalInstrument> originList,
			ArrayList<MusicalInstrument> destinationList) {
		for (MusicalInstrument originInstrument : originList) {
			if (originInstrument instanceof WindInstrument) {
				destinationList.add(originInstrument);
				setTotalPrice(totalPrice + originInstrument.getPrice().doubleValue());
				setSorted(false);
			}
		}
		System.out.println("\nAll Wind Instruments Added Successfully!");

	}

	@Override
	public void SortByBrandAndPrice(ArrayList<MusicalInstrument> list) {

		boolean sortFlag = true;
		MusicalInstrument tmp;

		if (!list.isEmpty()) {

			while (sortFlag) {

				sortFlag = false;
				for (int i = 0; i < list.size() - 1; i++) {

					if (list.get(i).compareTo(list.get(i + 1)) > 0) {
						tmp = list.get(i + 1);
						list.set(i + 1, list.get(i));
						list.set(i, tmp);
						sortFlag = true;
					}

				}
			}

			setSorted(true);
			System.out.println("\nInstruments Sorted Successfully!");
		} else {
			System.err.println("\nCannot Sort an Empty List!");
		}

	}

	@Override
	public int binnarySearchByBrandAndPrice(ArrayList<MusicalInstrument> list, String brand, Number price) {

		int left = 0;
		int mid;
		int right = list.size() - 1;

		while (left <= right) {
			mid = (left + right) / 2;

			if (brand.compareTo(list.get(mid).getBrand()) == 0) {
				if (price == null) {
					return mid;
				} else {
					if (price.doubleValue() == list.get(mid).getPrice().doubleValue() || ((right - left) == 1)) {
						return mid;
					} else if (price.doubleValue() < list.get(mid).getPrice().doubleValue()) {
						right = mid - 1;
					} else if (price.doubleValue() > list.get(mid).getPrice().doubleValue()) {
						left = mid + 1;
					}
				}

			} else if (brand.compareTo(list.get(mid).getBrand()) < 0) {
				right = mid - 1;
			} else if (brand.compareTo(list.get(mid).getBrand()) > 0) {
				left = mid + 1;
			}
		}
		return -1;
	}

	@Override
	public boolean addInstrument(ArrayList<MusicalInstrument> list, MusicalInstrument instrument) {
		list.add(instrument);
		setTotalPrice(totalPrice + instrument.getPrice().doubleValue());
		setSorted(false);
		return true;
	}

	@Override
	public boolean removeInstrument(ArrayList<MusicalInstrument> list, MusicalInstrument instrument) {
		if (list.contains(instrument)) {
			list.remove(instrument);
			setTotalPrice(totalPrice - instrument.getPrice().doubleValue());
			System.out.println("\nInstrument Deleted Successfully!");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean removeAll(ArrayList<MusicalInstrument> list) {
		if (list.isEmpty()) {
			return false;
		} else {
			list.clear();
			setTotalPrice(0);
			setSorted(false);
			System.out.println("\nAll Instruments Deleted Successfully!");
			return true;
		}
	}

	@Override
	public String toString() {
		return getInventoryList().toString();

	}

}
