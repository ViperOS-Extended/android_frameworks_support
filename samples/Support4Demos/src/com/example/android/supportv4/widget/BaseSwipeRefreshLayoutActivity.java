/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.supportv4.widget;

import com.example.android.supportv4.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Example of using the SwipeRefreshLayout.
 */
abstract class BaseSwipeRefreshLayoutActivity extends Activity implements OnRefreshListener {

    public static final String[] TITLES = {
            "Henry IV (1)",
            "Henry V",
            "Henry VIII",
            "Richard II",
            "Richard III",
            "Merchant of Venice",
            "Othello",
            "King Lear",
            "Henry IV (1)",
            "Henry V",
            "Henry VIII",
            "Richard II",
            "Richard III",
            "Merchant of Venice",
            "Othello",
            "King Lear",
            "Henry IV (1)",
            "Henry V",
            "Henry VIII",
            "Richard II",
            "Richard III",
            "Merchant of Venice",
            "Othello",
            "King Lear",
            "Henry IV (1)",
            "Henry V",
            "Henry VIII",
            "Richard II",
            "Richard III",
            "Merchant of Venice",
            "Othello",
            "King Lear"
    };

    // Try a SUPER quick refresh to make sure we don't get extra refreshes
    // while the user's finger is still down.
    private static final boolean SUPER_QUICK_REFRESH = false;

    private SwipeRefreshLayout mSwipeRefreshWidget;

    private final Handler mHandler = new Handler();

    private final Runnable mRefreshDone = new Runnable() {
        @Override
        public void run() {
            mSwipeRefreshWidget.setRefreshing(false);
        }

    };

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());

        mSwipeRefreshWidget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.color1, R.color.color2, R.color.color3,
                R.color.color4);
        mSwipeRefreshWidget.setOnRefreshListener(this);
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.swipe_refresh_menu, menu);
        return true;
    }

    /**
     * Click handler for the menu item to force a refresh.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch(id) {
            case R.id.force_refresh:
                mSwipeRefreshWidget.setRefreshing(true);
                refresh();
                return true;
        }
        return false;
    }

    private void refresh() {
        mHandler.removeCallbacks(mRefreshDone);
        mHandler.postDelayed(mRefreshDone, 1000);
    }
}