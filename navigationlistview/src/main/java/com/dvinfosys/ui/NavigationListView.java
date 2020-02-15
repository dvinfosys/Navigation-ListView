package com.dvinfosys.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.dvinfosys.adapter.NavigationListAdapter;
import com.dvinfosys.model.ChildModel;
import com.dvinfosys.model.HeaderModel;

import java.util.ArrayList;
import java.util.List;

public class NavigationListView extends ExpandableListView {

    private Context context;
    private int currentSelection = 0;
    private int currentChildSelection = -1;

    private List<HeaderModel> listHeader;
    private OnGroupClickListener onGroupClickListener;
    private OnChildClickListener onChildClickListener;
    private NavigationListAdapter adapter;

    public NavigationListView(Context context) {
        super(context);
    }

    public NavigationListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

    public NavigationListView init(Context context) {
        this.context = context;
        this.listHeader = new ArrayList<>();
        return this;
    }

    public NavigationListView setListMenu(List<HeaderModel> list) {
        if (list != null) {
            this.listHeader.addAll(list);
        }
        return this;
    }

    public NavigationListView addOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
        setOnGroupClickListener(this.onGroupClickListener);
        return this;
    }

    public NavigationListView addOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;
        setOnChildClickListener(this.onChildClickListener);
        return this;
    }

    public NavigationListView addHeaderModel(HeaderModel model) {
        if (model != null) {
            this.listHeader.add(model);
        }
        return this;
    }

    public NavigationListView build() {
        adapter = new NavigationListAdapter(this.context, this.listHeader);
        setAdapter(adapter);
        return this;
    }

    public void setSelected(int groupPosition) {
        HeaderModel headerModel = listHeader.get(groupPosition);

        if (!headerModel.isHasChild()) {
            HeaderModel currentModel = listHeader.get(currentSelection);
            currentModel.setSelected(false);

            if (currentChildSelection != -1) {
                ChildModel childModel = listHeader.get(currentSelection)
                        .getChildModelList().get(currentChildSelection);
                childModel.setSelected(false);

                currentChildSelection = -1;
            }

            headerModel.setSelected(true);

            currentSelection = groupPosition;
            adapter.notifyDataSetChanged();
        }
    }

    public void setSelected(int groupPosition, int childPosition) {

        HeaderModel currentModel = listHeader.get(currentSelection);
        currentModel.setSelected(false);

        if (currentChildSelection != -1) {
            ChildModel currentChildModel = listHeader
                    .get(currentSelection)
                    .getChildModelList()
                    .get(currentChildSelection);
            currentChildModel.setSelected(false);
        }

        currentSelection = groupPosition;
        currentChildSelection = childPosition;

        ChildModel childModel = listHeader.get(groupPosition).getChildModelList().get(childPosition);
        childModel.setSelected(true);
        adapter.notifyDataSetChanged();
    }
}
