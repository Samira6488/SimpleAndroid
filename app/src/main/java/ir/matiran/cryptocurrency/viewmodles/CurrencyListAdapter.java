package ir.matiran.cryptocurrency.viewmodles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedList;
import ir.matiran.cryptocurrency.databinding.WordlistItemBinding;
import ir.matiran.cryptocurrency.modle.ProfileListInfo;
import ir.matiran.cryptocurrency.MainActivity;


public class CurrencyListAdapter extends
        RecyclerView.Adapter<CurrencyListAdapter.WordViewHolder>{

    private final LinkedList<String> CurrencyList;
    private ItemClickListener clicklistner;
    //hint
    private final LayoutInflater mInflater;
    private ProfileListInfo profileList;

    public CurrencyListAdapter(MainActivity context,
                               LinkedList<String> wordList, ItemClickListener clicklistner) {
        mInflater = LayoutInflater.from(context);
        this.CurrencyList = wordList;
        this.clicklistner = clicklistner;
    }

    public void setProfileList(ProfileListInfo profileList)
    {
        this.profileList = profileList;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        WordlistItemBinding wordlistItemBinding = WordlistItemBinding.inflate(mInflater, parent, false);
        return new WordViewHolder(wordlistItemBinding, this, this.profileList);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String mCurrent = CurrencyList.get(position);
        holder.bindView(mCurrent);
    }

    @Override
    public int getItemCount() {
        return CurrencyList.size();
    }

    //*****************************************************************************

    public class WordViewHolder extends RecyclerView.ViewHolder  {

        final CurrencyListAdapter mAdapter;
        ProfileListInfo profileList;
        WordlistItemBinding wordlistItemBinding;

        public WordViewHolder(WordlistItemBinding wordlistItemBinding, CurrencyListAdapter adapter, ProfileListInfo profileList) {
            super(wordlistItemBinding.getRoot());
            this.wordlistItemBinding = wordlistItemBinding;
            this.profileList = profileList;
            this.mAdapter = adapter;
            //this.wordlistItemBinding.currencylistTv.setOnClickListener(this);

            wordlistItemBinding.currencylistTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mPosition = getLayoutPosition();
                    clicklistner.onItemClick(CurrencyList.get(mPosition));
                }
            });

        }
/*
        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in CurrencyList.
            String element = CurrencyList.get(mPosition);
            // Change the word in the mWordList.
            CurrencyList.set(mPosition, element);

            Context context = v.getContext();
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("SelectedCurrency", element );
            intent.putExtra("AllDetailList", this.profileList);
            context.startActivity(intent);

            // Notify the adapter that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();

        }

        */

        public void bindView(String CurrencyList){
            wordlistItemBinding.currencylistTv.setText(CurrencyList);
        }


    }

    public interface ItemClickListener{
        public void onItemClick(String CurrencyList );
    }
}
