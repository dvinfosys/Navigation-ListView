package com.dvinfosys.androidcustomnavigationdrawerusingexpandablelistview.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.dvinfosys.androidcustomnavigationdrawerusingexpandablelistview.Model.ChildModel;
import com.dvinfosys.androidcustomnavigationdrawerusingexpandablelistview.Model.HeaderModel;

import java.util.ArrayList;
import java.util.List;


public class ExpandableNavigationListView extends ExpandableListView {
    private Context context;
    private int currentSelection = 0;
    private int currentChildSelection = -1;

    private List<HeaderModel> listHeader;

    private OnGroupClickListener onGroupClickListener;
    private OnChildClickListener onChildClickListener;

    private ExpandableListAdapter expandableListAdapter;

    public ExpandableNavigationListView(Context context) {
        super(context);
    }

    public ExpandableNavigationListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandableNavigationListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = getMeasuredHeight();
    }

    public ExpandableNavigationListView init(Context context) {
        this.context = context;
        this.listHeader = new ArrayList<>();

        return this;
    }

    public ExpandableNavigationListView setListMenu(List<HeaderModel> listHeader) {

        if (listHeader != null)
            this.listHeader.addAll(listHeader);

        return this;
    }

    public ExpandableNavigationListView addOnGroupClickListener(OnGroupClickListener onGroupClickListener) {
        this.onGroupClickListener = onGroupClickListener;
        setOnGroupClickListener(this.onGroupClickListener);

        return this;
    }

    public ExpandableNavigationListView addOnChildClickListener(OnChildClickListener onChildClickListener) {
        this.onChildClickListener = onChildClickListener;

        setOnChildClickListener(this.onChildClickListener);
        return this;
    }

    public ExpandableNavigationListView addHeaderModel(HeaderModel headerModel) {
        this.listHeader.add(headerModel);
        return this;
    }

    public ExpandableNavigationListView build() {
        expandableListAdapter = new ExpandableListAdapter(this.context, this.listHeader);
        setAdapter(expandableListAdapter);

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
            expandableListAdapter.notifyDataSetChanged();
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
        expandableListAdapter.notifyDataSetChanged();
    }
}