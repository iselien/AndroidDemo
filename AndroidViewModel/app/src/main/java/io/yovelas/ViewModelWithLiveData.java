package io.yovelas;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void addNumber(int n){
        number.setValue(number.getValue() + n);
    }
}
