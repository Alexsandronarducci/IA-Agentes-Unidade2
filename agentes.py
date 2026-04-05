def agente_reativo_simples(percepcao):
    posicao_atual = percepcao['posicao']
    limites = percepcao['limites']
    
    if posicao_atual[0] == 0: atingiu_norte()
    if posicao_atual[0] == 9: atingiu_sul()
    if posicao_atual[1] == 0: atingiu_oeste()
    if posicao_atual[1] == 9: atingiu_leste()

    movimento = escolher_direcao_valida(limites)
    executar(movimento)

class AgenteModelo:
    def __init__(self):
        self.memoria_mapa = {}

    def decidir(self, percepcao):
        posicao = percepcao['posicao']
        vizinhos = percepcao['vizinhos']
        
        self.atualizar_modelo(posicao, vizinhos)
        
        alvo = selecionar_nao_visitado(self.memoria_mapa)
        if alvo:
            return mover_para(alvo)
        return explorar_novo()

def agente_objetivo(inicio, fim, mapa):
    caminho = calcular_rota_bfs(inicio, fim, mapa)
    
    for passo in caminho:
        executar_acao(passo)

def agente_utilidade(inicio, fim, mapa_custos, observavel=True):
    if observavel:
        rota = algoritmo_dijkstra(inicio, fim, mapa_custos)
    else:
        rota = mapeamento_incremental_custo(inicio, fim)
        
    return otimizar_utilidade(rota)
