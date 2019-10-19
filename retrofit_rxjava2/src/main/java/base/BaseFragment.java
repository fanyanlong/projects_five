package base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/*
 *Name：fanyanlong
 *Time：9:58
 *Name:day02_mvp_mode
 */public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view;
        view= View.inflate(getActivity(),getLayouId(),null);
        initView(view);
        initData();
        return view;
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getLayouId();
    
}
