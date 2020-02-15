# NavigationListView 
[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

This post is improvements to the previous posts discussed on customization of navigation drawer and highlighting specific row of expandable listview.

Since there are few comments regarding the highlight of expandable list view post, thought to show with new post using custom navigation drawer. But how can we see the highlight of the expandable list view in handset, let’s take navigation drawer and customize it to use expandable list view.

Before starting, I have faced few problems before starting expandablelistview and few questions are shown below. They are?
1.   How and why onchildclicklistener won’t respond but ongroupclicklistener responding?
2.   Whether we can have different background to the group view and child view?
3.   How to highlight the group row or child row on selecting?
4.   How to give the feedback on selection of other row when there is a highlight shown on the other row?

## Add it in your root build.gradle at the end of repositories:

          allprojects {
              repositories {
                ...
                maven { url 'https://jitpack.io' }
              }
            }

##  Add the dependency
```groovy
dependencies {
      implementation 'com.github.dvinfosys:Navigation-ListView:1.0.0'
}
```
Check out [NavigationListView releases](https://github.com/dvinfosys/Navigation-ListView/releases) to see more unstable versions.

you could customize following UI controls in your Android application

### xml

```xml
    <com.google.android.material.navigation.NavigationView
            android:id="@+id/nav_view"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_gravity="start"
            android:fitsSystemWindows="true"
            app:headerLayout="@layout/nav_header_main">
    
            <ScrollView
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="170dp">
    
                <com.dvinfosys.ui.NavigationListView
                    android:id="@+id/navigation_list_view"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:layout_gravity="left"
                    android:divider="@null"
                    android:groupIndicator="@null"
                    android:scrollbars="vertical" />
            </ScrollView>
    
        </com.google.android.material.navigation.NavigationView>
```
### java

```java
    NavigationListView listView= findViewById(R.id.navigation_list_view);
    listView.init(this)
                    .addHeaderModel(new HeaderModel("Home"))
                    .addHeaderModel(new HeaderModel("Cart",  R.drawable.ic_cardbackgroud, true,true, false, Color.WHITE))
                    .addHeaderModel(
                            new HeaderModel("Categories", -1,true)
                                    .addChildModel(new ChildModel("Men's Fashion"))
                                    .addChildModel(new ChildModel("Woman's Fashion"))
                                    .addChildModel(new ChildModel("Babies and Family"))
                                    .addChildModel(new ChildModel("Health"))
                    )
                    .addHeaderModel(new HeaderModel("Orders"))
                    .addHeaderModel(new HeaderModel("Wishlist"))
                    .addHeaderModel(new HeaderModel("Notifications"))
                    .build()
                    .addOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                        @Override
                        public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                            listView.setSelected(groupPosition);    
                            if (id == 0) {
                                //Home Menu
                                Common.showToast(context, "Home Select");
    
                                drawer.closeDrawer(GravityCompat.START);
                            } else if (id == 1) {
                                //Cart Menu
                                Common.showToast(context, "Cart Select");
                                drawer.closeDrawer(GravityCompat.START);
                            } /*else if (id == 2) {
                                //Categories Menu
                                Common.showToast(context, "Categories  Select");
                            }*/ else if (id == 3) {
                                //Orders Menu
                                Common.showToast(context, "Orders");
                                drawer.closeDrawer(GravityCompat.START);
                            } else if (id == 4) {
                                //Wishlist Menu
                                Common.showToast(context, "Wishlist Selected");
                                drawer.closeDrawer(GravityCompat.START);
                            } else if (id == 5) {
                                //Notifications Menu
                                Common.showToast(context, "Notifications");
                                drawer.closeDrawer(GravityCompat.START);
                            }
                            return false;
                        }
                    })
                    .addOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                            listView.setSelected(groupPosition, childPosition);
                            if (id == 0) {
                                Common.showToast(context, "Man's Fashion");
                            } else if (id == 1) {
                                Common.showToast(context, "Woman's Fashion");
                            } else if (id == 2) {
                                Common.showToast(context, "Babies and Family");
                            } else if (id == 3) {
                                Common.showToast(context, "Health");
                            }
    
                            drawer.closeDrawer(GravityCompat.START);
                            return false;
                        }
                    });
            //listView.expandGroup(2);
```

# Output

# ExpandableListView Show
<img src="https://github.com/dvinfosys/Android-Custom-Navigation-Drawer-Using-ExpandableListView/blob/master/SrceenShort/screenshot-2018-05-14_11.08.21.271.png" width="200px" heigth="200px">

# ExpandableListView Hide
<img src="https://github.com/dvinfosys/Android-Custom-Navigation-Drawer-Using-ExpandableListView/blob/master/SrceenShort/screenshot-2018-05-14_16.30.25.153.png" width="200px" heigth="200px">

