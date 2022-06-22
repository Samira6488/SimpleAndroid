package ir.matiran.cryptocurrency.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.LinkedList;
import ir.matiran.cryptocurrency.databinding.WordlistItemBinding;
import ir.matiran.cryptocurrency.model.ProfileListInfo;
import ir.matiran.cryptocurrency.MainActivity;


public class CurrencyListAdapter extends
        RecyclerView.Adapter<CurrencyListAdapter.WordViewHolder>{

    private final LinkedList<String> CurrencyList;
    private final ItemClickListener clicklistner;
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
        WordlistItemBinding binding = WordlistItemBinding.inflate(mInflater, parent, false);
        return new WordViewHolder(binding, this, this.profileList);
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

            wordlistItemBinding.currencylistTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int mPosition = getLayoutPosition();
                    clicklistner.onItemClick(CurrencyList.get(mPosition));
                }
            });
        }

        public void bindView(String CurrencyList){
            wordlistItemBinding.currencylistTv.setText(CurrencyList);
        }

    }

    public interface ItemClickListener{
        public void onItemClick(String CurrencyList );
    }
}
