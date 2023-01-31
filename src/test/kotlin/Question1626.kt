class Question1626 {
}

typealias Player = Pair<Int, Int>

private val Player.age get() = this.first
private val Player.score get() = this.second

fun bestTeamScore(scores: IntArray, ages: IntArray): Int {
    // 다시 생각해보자..
    // 넣으려는 Player 가 있다고 생각해보자.
    // Player 의 나이가 Player[i + 1] 보다 적은데, Player[i + 1] 의 score 보다도 적거나 같다면 반드시 넣을 수 있다.
    // 즉, 나이를 오름차순으로 정렬한뒤 conflict 조건만 보고 맞다면 넣게 되면 i 번째의 최대를 구할 수 있는 것이다.
    // 예외케이스로 나이가 같은데 앞선애가 작아서 안들어가지는 케이스 때문에 나이안에서도 점수별로 오름차순으로 해야함.
    // 즉, cache[i] 번째는 max(cache[i - 1] + x(0일 수도 값일수도), players[i] + ...players[..i](conflit 안나는 애들) 임) = cache[i] 임.
    // 즉, DP 로 풀이가 가능

    val cache = IntArray(scores.size)
    val players = mutableListOf<Player>()
    for (i in 0 until scores.size) {
        players.add(ages[i] to scores[i])
    }
    // 나이
    val sortedPlayers = sortedPlayersByAgeAndScore(increasingArraysByAge(players))

    var result = 0

    for (i in 0 until scores.size) {
        cache[i] = sortedPlayers[i].score
        for (j in 0 until i) {
            if (sortedPlayers[j].score <= sortedPlayers[i].score) {
                cache[i] = maxOf(cache[i], cache[j] + sortedPlayers[i].score)
            }
        }
        result = maxOf(result, cache[i])
    }

    return result
}

private fun sortedPlayersByAgeAndScore(players: Sequence<Map.Entry<Int, List<Player>>>) = players
    .map { it.value.sortedBy { it.score } }
    .flatten()
    .toList()

private fun increasingArraysByAge(players: MutableList<Player>) = players
    .groupBy { it.age }
    .entries
    .asSequence()
    .sortedBy { it.key }