#include <iostream>

using namespace std;

int M, N; // 세로, 가로 크기
int Map[502][502];
int dp[502][502];

int delx[4] = { 0,0,1,-1 };
int dely[4] = { 1,-1,0,0 };

int dfs(int y, int x) {
	if (y == M && x == N) return 1;
	if (dp[y][x] != -1)
		return dp[y][x];

	dp[y][x] = 0;
	
	for (int i = 0; i < 4; i++) {
		int nX = x + delx[i];
		int nY = y + dely[i];

		if (nX < 1 || nY < 1 || nY > M || nX > N || Map[y][x] <= Map[nY][nX])
			continue;
		dp[y][x] += dfs(nY, nX);
	}
	return dp[y][x];
}

int main() {
	scanf_s("%d %d", &M, &N);

	for (int i = 1; i <= M; i++) { //dp 초기화
		for (int j = 1; j <= N; j++) {
			dp[i][j] = -1;
		}
	}

	for (int i = 1; i <= M; i++) {
		for (int j = 1; j <= N; j++) {
			scanf_s("%d", &Map[i][j]);
		}
	}

	int ans = dfs(1, 1);

	printf("%d", ans);
}