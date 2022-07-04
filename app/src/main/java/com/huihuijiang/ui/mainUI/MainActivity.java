package com.huihuijiang.ui.mainUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import com.huihuijiang.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;
    private Drawer mainDrawer;
    private AccountHeader header;
    private NavController navController;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        initToolbar();
    }

    //初始化view
    private void initView(){
        toolbar=findViewById(R.id.toolbar);
    }

    //初始化toolbar和侧边栏
    @SuppressLint({"UseCompatLoadingForDrawables", "ResourceAsColor", "ResourceType"})
    private void initToolbar(){

        ProfileSettingDrawerItem mainProfile=new ProfileSettingDrawerItem()
                .withName("诸神助手")
                .withIdentifier(0)
                .withTag(0)
                .withIcon(getResources().getDrawable(R.drawable.ic_launcher_foreground))
                .withSelectedColorRes(R.color.slider_select_Color)
                .withTextColorRes(R.color.white);
        ProfileSettingDrawerItem adminProfile=new ProfileSettingDrawerItem()
                .withName("资深司机")
                .withIdentifier(1)
                .withTag(1)
                .withIcon(getResources().getDrawable(R.drawable.ic_launcher_foreground))
                .withSelectedColorRes(R.color.slider_select_Color)
                .withTextColorRes(R.color.black_gray4);
        //侧边栏头部账号布局
        header = new AccountHeaderBuilder()
                .withActivity(this)
                .withTextColorRes(R.color.header_text)
                .withHeaderBackground(R.color.colorPrimary)
                .addProfiles(
                        mainProfile.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                header.setActiveProfile(0);
                                mainProfile
                                        .withTextColorRes(R.color.white);
                                adminProfile
                                        .withTextColorRes(R.color.black_gray4);
                                return true;
                            }
                        }),
                        adminProfile.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                        header.setActiveProfile(1);
                                        adminProfile
                                                .withTextColorRes(R.color.white);
                                        mainProfile
                                                .withTextColorRes(R.color.black_gray4);
                                        return true;
                                    }
                                })
                )
                .withOnlyMainProfileImageVisible(true)
                .build();
        header.setActiveProfile(0);
        //侧边栏
        mainDrawer=new DrawerBuilder()
                .withActivity(this)
                .withSliderBackgroundDrawableRes(R.drawable.side_bg)
                .withToolbar(toolbar)
                .withAccountHeader(header)
                .addDrawerItems(
                        new PrimaryDrawerItem()
                                .withIdentifier(0)
                                .withName(R.string.drawer_item_home)
                                .withIcon(R.drawable.home)
                                .withSelectedTextColorRes(R.color.slider_select_textColor)
                                .withTextColorRes(R.color.slider_textColor)
                                .withSelectedColorRes(R.color.slider_select_Color),

                        new DividerDrawerItem(),

                        new SecondaryDrawerItem()
                                .withIdentifier(2)
                                .withName(R.string.drawer_item_cjb)
                                .withIcon(R.drawable.tag)
                                .withSelectedTextColorRes(R.color.slider_select_textColor)
                                .withTextColorRes(R.color.slider_textColor)
                                .withSelectedColorRes(R.color.slider_select_Color),
                        new SecondaryDrawerItem()
                                .withIdentifier(3)
                                .withName(R.string.drawer_item_ql)
                                .withIcon(R.drawable.tag)
                                .withSelectedTextColorRes(R.color.slider_select_textColor)
                                .withTextColorRes(R.color.slider_textColor)
                                .withSelectedColorRes(R.color.slider_select_Color),
                        new SecondaryDrawerItem()
                                .withIdentifier(4)
                                .withName(R.string.drawer_item_cz)
                                .withIcon(R.drawable.tag)
                                .withSelectedTextColorRes(R.color.slider_select_textColor)
                                .withTextColorRes(R.color.slider_textColor)
                                .withSelectedColorRes(R.color.slider_select_Color),

                        new DividerDrawerItem(),

                        new SecondaryDrawerItem()
                                .withIdentifier(1)
                                .withName(R.string.drawer_item_settings)
                                .withIcon(R.drawable.settings)
                                .withSelectedTextColorRes(R.color.slider_select_textColor)
                                .withTextColorRes(R.color.slider_textColor)
                                .withSelectedColorRes(R.color.slider_select_Color),
                        new SecondaryDrawerItem()
                                .withIdentifier(5)
                                .withName(R.string.drawer_item_about)
                                .withIcon(R.drawable.about)
                                .withSelectedTextColorRes(R.color.slider_select_textColor)
                                .withTextColorRes(R.color.slider_textColor)
                                .withSelectedColorRes(R.color.slider_select_Color),

                        new SectionDrawerItem()
                                .withName("Power by MD3 && huihuijiang")
                                .withEnabled(false)
                                .withTextColorRes(R.color.slider_textColor)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        switch ((int)drawerItem.getIdentifier()){
                            case 0:
                                toolbar.setTitle(R.string.drawer_item_home);
                                navController.navigate(R.id.mainFragment);
                                break;
                            case 1:
                                toolbar.setTitle(R.string.drawer_item_settings);
                                navController.navigate(R.id.settingsFragment);
                                break;
                            case 2:
                                toolbar.setTitle(R.string.drawer_item_cjb);
                                navController.navigate(R.id.CJBFragment);
                                break;
                            case 3:
                                toolbar.setTitle(R.string.drawer_item_ql);
                                navController.navigate(R.id.QLFragment);
                                break;
                            case 4:
                                toolbar.setTitle(R.string.drawer_item_cz);
                                navController.navigate(R.id.CZFragment);
                                break;
                            case 5:
                                toolbar.setTitle(R.string.drawer_item_about);
                                navController.navigate(R.id.aboutFragment);
                                break;
                        }
                        return false;
                    }
                })
                .build();
    }
}