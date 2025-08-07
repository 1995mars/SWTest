package org.mars.leetcode;

class NumArray {

    final static int MAX = Integer.MAX_VALUE;

    static int BITree[] = new int[MAX];
    int[] nums;

    public NumArray(int[] nums) {

        this.nums = nums;
        int n = nums.length;

        for (int i = 0; i <= n; i++) {
            BITree[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            updateBIT(i, nums[i]);
        }
    }

    public void updateBIT(int index, int val) {
        index ++;

        while (index <= nums.length) {
            BITree[index] += val;
            index += index & (-index);
        }
    }

    public void update(int index, int val) {
        int temp = val - this.nums[index];
        this.nums[index] = val;
        index ++;

        while (index <= nums.length) {
            BITree[index] += temp;
            index += index & (-index);
        }
    }

    public int sum(int index) {
        int sum = 0;

        index ++;
        while (index > 0) {
            sum += BITree[index];
            index -= index & (-index);
        }
        return sum;
    }

    public int sumRange(int left, int right) {
        return sum(right) - sum(left - 1);
    }

    public static void main(String[] args) {
        NumArray m = new NumArray(new int[] { 1, 3, 5});
        m.update(1,2);
        System.out.println(m.sum(2));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
