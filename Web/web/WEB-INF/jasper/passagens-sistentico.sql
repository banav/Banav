SELECT
	vvc.viagem_id,
	(select pt.nome from public.porto pt where pt.id = v.porto_origem) as origem,
	(select pt.nome from public.porto pt where pt.id = v.porto_destino) as destino,
	to_char(v.horaSaida, 'DD/MM/YYYY HH24:MI') as data,
	p.valor,
	vvc.classe as classe_id,
	(select c.nome from public.classe c where c.id = vvc.classe) as classe,
	p.gratuidade
FROM
	public.passagem p,
	public.viagem_valor_classe vvc,
	public.viagem v
WHERE
	p.viagem_valor_classe_id = vvc.id and
	p.id not in (select ph.passagem_id from public.passagem_historico ph where ph.passagem_id = p.id and ph.passagemmovimento = 'CANCELADA') and
	vvc.viagem_id = v.id and
	to_char(v.horaSaida, 'DD/MM/YYYY') = $P{dataInicio}
ORDER BY v.horaSaida, vvc.viagem_id, vvc.classe