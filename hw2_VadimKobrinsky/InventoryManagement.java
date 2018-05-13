// Name: Vadim Kobrinsky
// ID: 314257551

package hw2_VadimKobrinsky;

import java.util.ArrayList;

public interface InventoryManagement {

	public abstract void addAllStringInstruments(ArrayList<MusicalInstrument> originList,
			ArrayList<MusicalInstrument> destinationList);

	public abstract void addAllWindInstruments(ArrayList<MusicalInstrument> originList,
			ArrayList<MusicalInstrument> destinationList);

	public abstract void SortByBrandAndPrice(ArrayList<MusicalInstrument> list);

	public abstract int binnarySearchByBrandAndPrice(ArrayList<MusicalInstrument> list, String brand, Number price);

	public abstract boolean addInstrument(ArrayList<MusicalInstrument> list, MusicalInstrument instrument);

	public abstract boolean removeInstrument(ArrayList<MusicalInstrument> list, MusicalInstrument instrument);

	public abstract boolean removeAll(ArrayList<MusicalInstrument> list);
}
