# Cidade em Dia de Chuva — GS 2026.2 

## Integrantes

| Nome | RM |
|---|---|
| Felipe Slavic | 565687 |
| Leonardo Laurentino | 561455 |

**RM da equipe:** 565687 + 561455 = **1127142**

## Parâmetros (divisão inteira)

| Parâmetro | Cálculo | Resultado |
|---|---|---|
| P1 — prefixo das tabelas | 1127142 % 1000 = 142 | **GS142** → GS142_BAIRRO, GS142_TRECHO, GS142_LEITURA |
| P2 — limiar de alerta L | 10 + 2 × (1127142 % 4) = 10 + 2 × 2 | **L = 14 cm** (atenção a partir de 7 cm) |
| P3 — atributo extra de Bairro | (1127142 / 10) % 3 = 112714 % 3 = 1 | **populacao** (Integer, ≥ 0) |
| P4 — ordenação dos trechos | (1127142 / 100) % 3 = 11271 % 3 = 0 | **logradouro** (A a Z) |
| P5 — exclusão de trecho com leituras | (1127142 / 1000) % 2 = 1127 % 2 = 1 | **cascata** (leituras e depois o trecho) |

## Versão escolhida

Versão **web** (@Controller + Thymeleaf).

## Forma dos DTOs

Um DTO por entidade (BairroDTO, TrechoDTO, LeituraDTO) e o PainelItemDTO, todos como **record**, que foi a forma que vimos no material de JPA.

## Observações


## Como executar

1. Definir as variáveis de ambiente (IntelliJ: Run > Edit Configurations > Environment variables):
`DB_USER=rmXXXXXX;DB_PASSWORD=...`

2. Executar `Felipe565687Application` e abrir http://localhost:8080

3. Conferir no SQL Developer:
   `SELECT table_name FROM user_tables WHERE table_name LIKE 'GS142%';`

## Resultado esperado do roteiro (L = 14)

| Trecho | Lâmina | Status |
|---|---|---|
| Rua Alta | 5 cm | NORMAL |
| Travessa da Ponte | 7 cm | ATENCAO |
| Rua das Palmeiras | 11 cm | ATENCAO |
| Rua do Córrego | 13 cm | ATENCAO |
| Av. Beira-Rio | 15 cm → 3 cm (passo 5) | ALERTA → NORMAL |

- Lista de trechos (P4 = 0): Av. Beira-Rio, Rua Alta, Rua das Palmeiras, Rua do Córrego, Travessa da Ponte
- Painel de Vila Esperança (passo 6): NORMAL 1 · ATENCAO 3 · ALERTA 0
- Passo 7: exclusão do bairro recusada (R2)
- Passo 8: Rua Alta e sua leitura excluídas (P5 = 1)
- Passo 10: GS142_LEITURA com 3 linhas