ALTER TABLE public.tb_consulta
ADD COLUMN status varchar(25) DEFAULT 'PENDENTE_PAGAMENTO' NOT NULL;

ALTER TABLE public.tb_consulta_aud
ADD COLUMN status varchar(25);

ALTER TABLE public.tb_consulta
ADD CONSTRAINT chk_status_pagamento
CHECK (status IN ('PENDENTE_PAGAMENTO', 'APROVADO_PAGAMENTO', 'REPROVADO_PAGAMENTO'));

ALTER TABLE public.tb_consulta
ADD COLUMN valor NUMERIC(13, 2) NOT NULL;

ALTER TABLE public.tb_consulta_aud
ADD COLUMN valor NUMERIC(13, 2) NOT NULL;