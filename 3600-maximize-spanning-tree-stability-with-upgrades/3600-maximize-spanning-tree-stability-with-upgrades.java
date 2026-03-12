class Solution {
    int[] parent, rank;

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int px = find(x), py = find(y);
        if (px == py) return false;
        if (rank[px] < rank[py]) { int t = px; px = py; py = t; }
        parent[py] = px;
        if (rank[px] == rank[py]) rank[px]++;
        return true;
    }

    public int maxStability(int n, int[][] edges, int k) {
        parent = new int[n];
        rank = new int[n];

        // Add all must edges, check for cycle
        for (int i = 0; i < n; i++) parent[i] = i;
        int mn = Integer.MAX_VALUE;
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (!union(e[0], e[1])) return -1;
                mn = Math.min(mn, e[2]);
            }
        }

        // Check if all nodes can be connected
        int[] tempParent = parent.clone();
        int[] tempRank = rank.clone();
        for (int[] e : edges) {
            if (e[3] == 0) union(e[0], e[1]);
        }
        int components = 0;
        for (int i = 0; i < n; i++) if (find(i) == i) components++;
        if (components > 1) return -1;

        if (mn == Integer.MAX_VALUE) mn = Integer.MAX_VALUE / 2;

        // Binary search on answer
        int lo = 1, hi = mn, ans = 0;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (check(n, edges, k, mid)) { ans = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return ans;
    }

    boolean check(int n, int[][] edges, int k, int lim) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // Add must edges and strong optional edges
        for (int[] e : edges) {
            if (e[3] == 1) {
                if (e[2] < lim) return false;
                union(e[0], e[1]);
            } else if (e[2] >= lim) {
                union(e[0], e[1]);
            }
        }

        // Use upgrades for edges where 2*s >= lim
        int upgrades = k;
        for (int[] e : edges) {
            if (upgrades == 0) break;
            if (e[3] == 0 && e[2] < lim && e[2] * 2 >= lim) {
                if (union(e[0], e[1])) upgrades--;
            }
        }

        for (int i = 0; i < n; i++) if (find(i) == i && i != find(0)) return false;
        return true;
    }
}